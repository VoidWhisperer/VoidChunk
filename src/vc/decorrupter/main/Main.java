/**
Copyright (C) 2012 VoidWhisperer

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see http://www.gnu.org/licenses/.
**/
package vc.decorrupter.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mojang.nbt.NBTCompressionUtility;
import com.mojang.nbt.NBTTagByteArray;
import com.mojang.nbt.NBTTagCompound;
import com.mojang.nbt.NBTTagList;
import com.mojang.nbt.NibbleArray;
/**
 * @author VoidWhisperer
 *
 */
/**TODO:
Add verbose logging
Tnt clean up
Entity clean up
etc
*/
public class Main {
	public static boolean debug = true;
	public static boolean changed = false;
	public static BufferedWriter out = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Please select decorruption type. (lc = lighting,t = tnt cleanup(ni),e = entity removal(ni),pl = phantom lighting (See thread for more info on phantom lighting,gc = general corruption - looks for general corruption that can cause map failures. bc = block corruptions. Do op if you want to search for most of the types of corruptions in one pass.");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String text = "";
			try {
				out = new BufferedWriter(new FileWriter("decorrupter.log",true));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				text = in.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(text.equalsIgnoreCase("lc"))
			{
				System.out.println("Please input the directory of the world you would like to decorrupt.");
				try {
					String text2 = in.readLine();
					LightingCorruption.cleanup(text2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(text.equalsIgnoreCase("t"))
			{
				System.out.println("Please input the directory of the world you would like to decorrupt.");
				try {
					String text2 = in.readLine();
					TntRemoval.cleanup(text2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if(text.equalsIgnoreCase("e"))
			{
				System.out.println("Please input the directory of the world you would like to decorrupt.");
				try {
					String text2 = in.readLine();
					ClearEntities.cleanup(text2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if(text.equalsIgnoreCase("pl"))
			{
				//wd
				System.out.println("Please input the directory of the world you would like to decorrupt.");
				try {
					String text2 = in.readLine();
					PhantomLighting.cleanup(text2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if(text.equalsIgnoreCase("gc"))
			{
				System.out.println("Please input the directory of the world you would like to decorrupt.");
				try {
					String text2 = in.readLine();
					GeneralCorruption.cleanup(text2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if(text.equalsIgnoreCase("bc"))
			{
				System.out.println("Please input the directory of the world you would like to decorrupt.");
				try {
					String text2 = in.readLine();
					BlockCorruption.cleanup(text2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(text.equalsIgnoreCase("op"))
			{
				System.out.println("Please input the directory of the world you would like to decorrupt.");
				try {
					String text2 = in.readLine();
					OnePass.cleanup(text2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	}
	static byte[] toBytes(int i)
	{
	  byte[] result = new byte[4];

	  result[0] = (byte) (i >> 24);
	  result[1] = (byte) (i >> 16);
	  result[2] = (byte) (i >> 8);
	  result[3] = (byte) (i /*>> 0*/);

	  return result;
	}
}
