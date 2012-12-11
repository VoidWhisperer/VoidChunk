package com.mojang.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagShort extends NBTBase {

    public short data;

    public NBTTagShort(String s) {
        super(s);
    }

    public NBTTagShort(String s, short short1) {
        super(s);
        this.data = short1;
    }

    void write(DataOutput dataoutput) {
        try {
			dataoutput.writeShort(this.data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    void load(DataInput datainput) {
        try {
			this.data = datainput.readShort();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public byte getTypeId() {
        return (byte) 2;
    }

    public String toString() {
        return "" + this.data;
    }

    public NBTBase clone() {
        return new NBTTagShort(this.getName(), this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagShort nbttagshort = (NBTTagShort) object;

            return this.data == nbttagshort.data;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return super.hashCode() ^ this.data;
    }
}
