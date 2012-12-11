package com.mojang.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagByte extends NBTBase {

    public byte data;

    public NBTTagByte(String s) {
        super(s);
    }

    public NBTTagByte(String s, byte b0) {
        super(s);
        this.data = b0;
    }

    void write(DataOutput dataoutput) {
        try {
			dataoutput.writeByte(this.data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    void load(DataInput datainput) {
        try {
			this.data = datainput.readByte();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public byte getTypeId() {
        return (byte) 1;
    }

    public String toString() {
        return "" + this.data;
    }

    public NBTBase clone() {
        return new NBTTagByte(this.getName(), this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagByte nbttagbyte = (NBTTagByte) object;

            return this.data == nbttagbyte.data;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return super.hashCode() ^ this.data;
    }
}
