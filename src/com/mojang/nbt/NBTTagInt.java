package com.mojang.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagInt extends NBTBase {

    public int data;

    public NBTTagInt(String s) {
        super(s);
    }

    public NBTTagInt(String s, int i) {
        super(s);
        this.data = i;
    }

    void write(DataOutput dataoutput) {
        try {
			dataoutput.writeInt(this.data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    void load(DataInput datainput) {
        try {
			this.data = datainput.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public byte getTypeId() {
        return (byte) 3;
    }

    public String toString() {
        return "" + this.data;
    }

    public NBTBase clone() {
        return new NBTTagInt(this.getName(), this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagInt nbttagint = (NBTTagInt) object;

            return this.data == nbttagint.data;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return super.hashCode() ^ this.data;
    }
}
