package src;

public class EquipInfo implements Comparable<EquipInfo>{ // make object which save spended meso
    public int sfLevel;
    public int equipmentLv;
    public long spendMeso = 0; // save result data
    public int spendEquip = 0; // save result data 2

    public static final int SIZE = 10000000;
    public int realSize = 0;

    public String[] logs = new String[SIZE];

    EquipInfo(int equipmentLv){
        sfLevel = 0;
        this.equipmentLv = equipmentLv;
    }

    public int compareTo(EquipInfo equip){
        if(spendMeso > equip.spendMeso){
            return 1;
        }
        else if(spendMeso == equip.spendMeso){
            if(spendEquip > equip.spendEquip){
                return 1;
            }
            else{
                return -1;
            }
        }
        else{
            return -1;
        }
    }
}