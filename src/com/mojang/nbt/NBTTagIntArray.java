package com.mojang.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class NBTTagIntArray extends NBTBase {

    public int[] data;

    public NBTTagIntArray(String s) {
        super(s);
    }

    public NBTTagIntArray(String s, int[] aint) {
        super(s);
        this.data = aint;
    }

    void write(DataOutput dataoutput) {
        try {
			dataoutput.writeInt(this.data.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int[] aint = this.data;
        int i = aint.length;

        for (int j = 0; j < i; ++j) {
            int k = aint[j];

            try {
				dataoutput.writeInt(k);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    void load(DataInput datainput) {
        int i = 0;
		try {
			i = datainput.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        this.data = new int[i];

        for (int j = 0; j < i; ++j) {
        	try
        	{
            this.data[j] = datainput.readInt();
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        }
    }

    public byte getTypeId() {
        return (byte) 11;
    }

    public String toString() {
        return "[" + this.data.length + " bytes]";
    }

    public NBTBase clone() {
        int[] aint = new int[this.data.length];

        System.arraycopy(this.data, 0, aint, 0, this.data.length);
        return new NBTTagIntArray(this.getName(), aint);
    }

    public boolean equals(Object object) {
        if (!super.equals(object)) {
            return false;
        } else {
            NBTTagIntArray nbttagintarray = (NBTTagIntArray) object;

            return this.data == null && nbttagintarray.data == null || this.data != null && Arrays.equals(this.data, nbttagintarray.data);
        }
    }

    public int hashCode() {
        return super.hashCode() ^ Arrays.hashCode(this.data);
    }
}
