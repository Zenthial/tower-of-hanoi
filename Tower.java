import java.util.ArrayList;

class Tower {
    private int numDisks;
    private int moveNum = 1;
    private ArrayList<Disk> pole1 = new ArrayList<Disk>();
    private ArrayList<Disk> pole2 = new ArrayList<Disk>();
    private ArrayList<Disk> pole3 = new ArrayList<Disk>();
  
    Tower(int disks) {
        System.out.println(disks);
        this.numDisks = disks;
      
        for (int i = 1; i <= disks; i++) {
            Disk newDisk = new Disk(i * 10);
            pole1.add(newDisk);
            System.out.println(i);
        }
    }
  
    public void Start() {
        GetBestMove();
    }
    
    private void GetBestMove() {
        boolean open3 = CheckIfOpen(pole3);
        boolean open2 = CheckIfOpen(pole2);
        boolean open1 = CheckIfOpen(pole1);

        if (open3 && !open1) {
            Disk topDisk = pole1.get(0);
            pole1.remove(0);
            pole3.add(topDisk);
            Log(topDisk, 1, 3);
        } else if (open2 && !open1) {
            Disk topDisk = pole1.get(0);
            pole1.remove(0);
            pole2.add(topDisk);
            Log(topDisk, 1, 2);
        } else if (open2 && open1) {
            System.out.println("Solved!");
            return;
        } else {
            return;
        }

        if (!(open1 && open2 && open3)) {
            Disk top1 = pole1.get(0);
            Disk top2 = pole2.get(0);
            Disk top3 = pole3.get(0);
            if (top3.weight < top2.weight && top3.weight < top1.weight && top2.weight < top1.weight) {
                pole2.add(top3);
                pole3.remove(0);
                Log(top3, 3, 2);
            }
        }

        GetBestMove();
    }

    private boolean CheckIfOpen(ArrayList<Disk> pole) {
        if (pole.size() == 0)
            return true;
        else
            return false;
    }

    private void Log(Disk disk, int original, int goal) {
        System.out.println(moveNum + ": Move Weighted Disk " + disk.weight + " from pole " + original + " to pole" + goal);
        moveNum++; 
    }
  }