/**
Copyright (C) 2012 VoidWhisperer

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHMain.out ANY WARRANTY; withMain.out even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see http://www.gnu.org/licenses/.
**/
package vc.decorrupter.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mojang.nbt.NBTCompressionUtility;
import com.mojang.nbt.NBTTagCompound;
import com.mojang.nbt.NBTTagList;
import com.mojang.nbt.NibbleArray;

/**
 * @author VoidWhisperer
 *
 */
public class LightingCorruption {
	public static boolean changed = false;
	public static boolean debug = true;
	public static void cleanup(String text)
	{
		File f = new File(text);
		Pattern regionFilePattern = Pattern.compile("r\\.(-?[0-9]+)\\.(-?[0-9]+)\\.mca");
		for(File file : f.listFiles())
		{
			Matcher match = regionFilePattern.matcher(file.getName());
			if(match.matches())
			{
				RegionFile origRegion = new RegionFile(file);
				File newFile = new File(file.getPath() + ".rebuilt");
				//FileInputStream f = new FileInputStream(new File(text));
				RegionFile rebuiltRegion = new RegionFile(newFile);
				//rebuiltRegion = origRegion;
				System.out.println("Parsing file " + file.getName() + "...pre-build length of " + file.length());
				try {
					Main.out.append("Parsing file " + file.getName() + "...pre-build length of " + file.length());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					Main.out.newLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int regionX = Integer.parseInt(match.group(1));
				int regionZ = Integer.parseInt(match.group(2));
				for(int x = 0; x < 32; x++)
				{
					for(int z = 0; z < 32; z++)
					{
						int chunkX = x + (regionX << 5);
						int chunkZ = z + (regionZ << 5);
						if(origRegion.getOffset(x, z) == 0)
						{
							continue;
						}
						DataInputStream chunkInputStream = origRegion.getChunkDataInputStream(x, z);
						if(chunkInputStream == null)
						{
							try
							{
							//Main.out.append("Debug2");
							//Main.out.newLine();
							}catch(Exception e)
							{
								e.printStackTrace();
							}
							continue;
						}
						try
						{
							NBTTagCompound rootTag = NBTCompressionUtility.readRootTagCompound(chunkInputStream);
							if(!rootTag.hasKey("Level"))
							{
								System.out.println("Chunk file at " + chunkX + "," + chunkZ + " is missing level data, skipping!");
								Main.out.append("Chunk file at " + chunkX + "," + chunkZ + " is missing level data, skipping!");
								Main.out.newLine();
							}else{
								NBTTagCompound levelTag = rootTag.getCompound("Level");
								int xPos = levelTag.getInt("xPos");
								int zPos = levelTag.getInt("zPos");
								//NBTTagList tagList = levelTag.getLi st("Entities");
								if(xPos == chunkX && zPos == chunkZ)
								{
									//Main.out.append("Debug3");
									Main.out.newLine();
									NBTTagList tagList = levelTag.getList("Sections");
									for(int i = 0; i < tagList.size(); i++)
									{
										System.out.println("Scanning chunk "+xPos+","+zPos+" section " + i + " for corruption");
										Main.out.append("Scanning chunk "+xPos+","+zPos+" section " + i + " for corruption");
										Main.out.newLine();
										NBTTagCompound section = (NBTTagCompound) tagList.get(i);
										//byte[] blockLight = section.getByteArray("SkyLight");
										NibbleArray skyLight = new NibbleArray(section.getByteArray("SkyLight"),4);
										if(section.getByteArray("SkyLight").length < 2048)
										{
											System.out.println("Corrupted chunk detected @ " + xPos + "," + zPos +" in section " + i +". Fixing.");
											Main.out.append("Corrupted chunk detected @ " + xPos + "," + zPos +" in section " + i +". Fixing.");
											Main.out.newLine();
											section.setByteArray("SkyLight", new byte[2048]);
											changed = true;
										}
										NibbleArray blockLight = new NibbleArray(section.getByteArray("BlockLight"),4);
										if(section.getByteArray("BlockLight").length < 2048)
										{
											System.out.println("Corrupted chunk detected @ " + xPos + "," + zPos +" in section " + i +". Fixing.");
											Main.out.append("Corrupted chunk detected @ " + xPos + "," + zPos +" in section " + i +". Fixing.");
											Main.out.newLine();
											section.setByteArray("BlockLight", new byte[2048]);
											changed = true;
										}
									}
									DataOutputStream chunkOutputStream = rebuiltRegion.getChunkDataOutputStream(x, z);
									NBTCompressionUtility.writeRootTagCompound(rootTag, chunkOutputStream);
									chunkOutputStream.close();
								}
								//for(int i = 0; i < tagList.size(); i++)
								///{
								//	NBTTagCompound entity = (NBTTagCompound)tagList.get(i);
									//System.out.println(entity.getString("id"));
								//}
								//System.out.println(xPos + "," + zPos);
							}
						}catch(Exception e)
						{
							e.printStackTrace();
						}
						//System.out.println("Treated lightning corruptions in chunk.. x = "+chunkX+",z="+chunkZ);
					}
				}
				try
				{
					origRegion.close();
					rebuiltRegion.close();
					
					System.out.println("Rebuilt file length: " + file.length());
					Main.out.append("Rebuilt file length: " + file.length());
					Main.out.newLine();
					if(changed)
					{
						if(file.delete())
						{
							if(debug)
							{
								System.out.println("Deleted original file.");
								Main.out.append("Deleted original file.");
								Main.out.newLine();
							}
						}
					}else{
						if(newFile.delete())
						{
							if(debug)
							{
								System.out.println("Deleted copied file, due to there being no changes.");
								Main.out.append("Deleted copied file, due to there being no changes.");
								Main.out.newLine();
							}
						}
					}
					if(changed)
					{
						if(newFile.renameTo(file))
						{
							if(debug)
							{
								System.out.println("Renamed rebuilt region file to the orginal's name.");
								Main.out.append("Renamed rebuilt region file to the orginal's name.");
								Main.out.newLine();
							}
						}
					}
				}catch(IOException e)
				{
					e.printStackTrace();
				}
				//origRegion.
		}
		}
		try {
			Main.out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Main.out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
