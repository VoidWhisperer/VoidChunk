package com.mojang.nbt;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class NBTCompressedStreamTools {

    public static NBTTagCompound a(InputStream inputstream) {
        DataInputStream datainputstream = null;
		try {
			datainputstream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(inputstream)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        NBTTagCompound nbttagcompound;

        try {
            nbttagcompound = a((DataInput) datainputstream);
        } finally {
            try {
				datainputstream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return nbttagcompound;
    }

    public static void a(NBTTagCompound nbttagcompound, OutputStream outputstream) {
        DataOutputStream dataoutputstream = null;
		try {
			dataoutputstream = new DataOutputStream(new GZIPOutputStream(outputstream));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
            a(nbttagcompound, (DataOutput) dataoutputstream);
        } finally {
            try {
				dataoutputstream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    public static NBTTagCompound a(byte[] abyte) {
        DataInputStream datainputstream = null;
		try {
			datainputstream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(abyte))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        NBTTagCompound nbttagcompound;

        try {
            nbttagcompound = a((DataInput) datainputstream);
        } finally {
            try {
				datainputstream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return nbttagcompound;
    }

    public static byte[] a(NBTTagCompound nbttagcompound) {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        DataOutputStream dataoutputstream = null;
		try {
			dataoutputstream = new DataOutputStream(new GZIPOutputStream(bytearrayoutputstream));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
            a(nbttagcompound, (DataOutput) dataoutputstream);
        } finally {
            try {
				dataoutputstream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return bytearrayoutputstream.toByteArray();
    }

    public static NBTTagCompound a(DataInput datainput) {
        NBTBase nbtbase = NBTBase.b(datainput);

        if (nbtbase instanceof NBTTagCompound) {
            return (NBTTagCompound) nbtbase;
        } else {
            try {
				throw new IOException("Root tag must be a named compound tag");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return null;
    }

    public static void a(NBTTagCompound nbttagcompound, DataOutput dataoutput) {
        try {
			NBTBase.a(nbttagcompound, dataoutput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
