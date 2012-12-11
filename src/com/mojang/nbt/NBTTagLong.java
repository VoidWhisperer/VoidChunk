package com.mojang.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagLong extends NBTBase {

    public long data;

    public NBTTagLong(String s) {
        super(s);
    }

    public NBTTagLong(String s, long i) {
        super(s);
        this.data = i;
    }

    void write(DataOutput dataoutput) {
        try {
			dataoutput.writeLong(this.data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    void load(DataInput datainput) {
        try {
			this.data = datainput.readLong();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public byte getTypeId() {
        return (byte) 4;
    }

    public String toString() {
        return "" + this.data;
    }

    public NBTBase clone() {
        return new NBTTagLong(this.getName(), this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagLong nbttaglong = (NBTTagLong) object;

            return this.data == nbttaglong.data;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return super.hashCode() ^ (int) (this.data ^ this.data >>> 32);
    }
}
