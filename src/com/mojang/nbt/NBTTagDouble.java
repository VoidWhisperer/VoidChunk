package com.mojang.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagDouble extends NBTBase {

    public double data;

    public NBTTagDouble(String s) {
        super(s);
    }

    public NBTTagDouble(String s, double d0) {
        super(s);
        this.data = d0;
    }

    void write(DataOutput dataoutput) {
        try {
			dataoutput.writeDouble(this.data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    void load(DataInput datainput) {
        try {
			this.data = datainput.readDouble();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public byte getTypeId() {
        return (byte) 6;
    }

    public String toString() {
        return "" + this.data;
    }

    public NBTBase clone() {
        return new NBTTagDouble(this.getName(), this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagDouble nbttagdouble = (NBTTagDouble) object;

            return this.data == nbttagdouble.data;
        } else {
            return false;
        }
    }

    public int hashCode() {
        long i = Double.doubleToLongBits(this.data);

        return super.hashCode() ^ (int) (i ^ i >>> 32);
    }
}
