package com.mojang.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagFloat extends NBTBase {

    public float data;

    public NBTTagFloat(String s) {
        super(s);
    }

    public NBTTagFloat(String s, float f) {
        super(s);
        this.data = f;
    }

    void write(DataOutput dataoutput) {
        try {
			dataoutput.writeFloat(this.data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    void load(DataInput datainput) {
        try {
			this.data = datainput.readFloat();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public byte getTypeId() {
        return (byte) 5;
    }

    public String toString() {
        return "" + this.data;
    }

    public NBTBase clone() {
        return new NBTTagFloat(this.getName(), this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagFloat nbttagfloat = (NBTTagFloat) object;

            return this.data == nbttagfloat.data;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return super.hashCode() ^ Float.floatToIntBits(this.data);
    }
}
