package src;

public class EquipInfo implements Comparable<EquipInfo>{
    public int level;
    public int equipmentLv;
    public long spendMeso = 0;

    EquipInfo(int level, int equipmentLv){
        this.level = level;
        this.equipmentLv = equipmentLv;
    }

    public int getNeedMeso(int level){
        int meso = 0;

        if(level <= 9){
            meso = (int) (1000 + Math.pow(level, 3)*(level+1)/25);
        }
        else if(level > 9 && level < 15){
            meso = (int) (1000 + Math.pow(level, 3)*Math.pow((level+1), 2.7)/400);
        }
        else{
            meso = (int) (1000 + Math.pow(level, 3)*Math.pow((level+1), 2.7)/200);
        }

        return meso;
    }

    public int compareTo(EquipInfo equip){
        if(spendMeso > equip.spendMeso){
            return 1;
        }
        else if(spendMeso == equip.spendMeso){
            return 0;
        }
        else{
            return -1;
        }
    }
}