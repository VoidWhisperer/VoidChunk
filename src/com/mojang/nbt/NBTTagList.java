package com.mojang.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NBTTagList extends NBTBase {

    private List list = new ArrayList();
    private byte type;

    public NBTTagList() {
        super("");
    }

    public NBTTagList(String s) {
        super(s);
    }

    void write(DataOutput dataoutput) {
        if (!this.list.isEmpty()) {
            this.type = ((NBTBase) this.list.get(0)).getTypeId();
        } else {
            this.type = 1;
        }

        try {
			dataoutput.writeByte(this.type);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			dataoutput.writeInt(this.list.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Iterator iterator = this.list.iterator();

        while (iterator.hasNext()) {
            NBTBase nbtbase = (NBTBase) iterator.next();

            nbtbase.write(dataoutput);
        }
    }
    public int remove(String type)
    {
    	int removed = 0;
    	Iterator a = list.iterator();
    	while(a.hasNext())
    	{
    		try
    		{
    			if(!type.equalsIgnoreCase("all"))
    			{
	    			NBTTagCompound tagc2 = (NBTTagCompound) a.next();
	    			if(tagc2.getString("id").equalsIgnoreCase(type))
	    			{
	    				a.remove();
	    				removed+=1;
	    			}
    			}else{
    				a.next();
    				a.remove();
    				removed+=1;
    			}
    		}catch(Exception e)
    		{
    			//Well it wasn't a compound then! =..=
    		}
    	}
    	return removed;
    }
    void load(DataInput datainput) {
        try {
			this.type = datainput.readByte();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int i = 0;
		try {
			i = datainput.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        this.list = new ArrayList();

        for (int j = 0; j < i; ++j) {
            NBTBase nbtbase = NBTBase.createTag(this.type, (String) null);

            nbtbase.load(datainput);
            this.list.add(nbtbase);
        }
    }

    public byte getTypeId() {
        return (byte) 9;
    }

    public String toString() {
        return "" + this.list.size() + " entries of type " + NBTBase.getTagName(this.type);
    }

    public void add(NBTBase nbtbase) {
        this.type = nbtbase.getTypeId();
        this.list.add(nbtbase);
    }

    public NBTBase get(int i) {
        return (NBTBase) this.list.get(i);
    }

    public int size() {
        return this.list.size();
    }

    public NBTBase clone() {
        NBTTagList nbttaglist = new NBTTagList(this.getName());

        nbttaglist.type = this.type;
        Iterator iterator = this.list.iterator();

        while (iterator.hasNext()) {
            NBTBase nbtbase = (NBTBase) iterator.next();
            NBTBase nbtbase1 = nbtbase.clone();

            nbttaglist.list.add(nbtbase1);
        }

        return nbttaglist;
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagList nbttaglist = (NBTTagList) object;

            if (this.type == nbttaglist.type) {
                return this.list.equals(nbttaglist.list);
            }
        }

        return false;
    }

    public int hashCode() {
        return super.hashCode() ^ this.list.hashCode();
    }
}
