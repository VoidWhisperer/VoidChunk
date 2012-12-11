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
package com.mojang.nbt;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * @author VoidWhisperer
 *
 */
public class NBTCompressionUtility {
	private static NBTTagCompound localNBTTagCompound1;

	public static NBTTagCompound readGzippedCompoundFromInputStream(InputStream inputstream) throws IOException
	{
		DataInputStream datainputstream = new DataInputStream(new GZIPInputStream(inputstream));
		try
		{
			NBTTagCompound nbtTagCompound = readRootTagCompound(datainputstream);
			NBTTagCompound localNBTCompound1 = nbtTagCompound;
			return localNBTTagCompound1; } finally { datainputstream.close(); }
		}

	/**
	 * @param datainputstream
	 * @return
	 */
	public static NBTTagCompound readRootTagCompound(DataInput datainput) throws IOException {
		NBTBase nbtbase = NBTBase.b(datainput);
		if(nbtbase instanceof NBTTagCompound)
		{
			return (NBTTagCompound)nbtbase;
		}
		throw new IOException("Root tag must be a named compound tag");
	}
	
	public static void writeRootTagCompound(NBTTagCompound nbttagcompound, DataOutput dataoutput) throws IOException
	{
		NBTBase.a(nbttagcompound, dataoutput);
	}
}
