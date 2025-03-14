import javax.swing.plaf.metal.MetalBorders.PaletteBorder;

public  abstract class BattleLoc extends Location {
    protected Obstacle obstacle;
    protected String award;

    BattleLoc(Player player, String name,Obstacle obstacle,String award) {
        super(player);
        this.obstacle=obstacle;
        this.name=name;
        this.award=award;
    }
    public boolean getLocation() {
        int obsCount = obstacle.count();
        System.out.println("Şuan buradasiniz : " + this.getName());
        System.out.println("Dikkatli ol! Burada " + obsCount + " tane " + obstacle.getName() + " yaşiyor !");
        System.out.print("<S>avaş veya <K>aç : ");
        String selCase = scan.nextLine();
        selCase=selCase.toUpperCase();
        if (selCase.equals("S")) {
            if (combat(obsCount)) {
                System.out.println(this.getName() + " bölgesindeki tüm düşmanlari temizlediniz !"); 
                if (this.award.equals("Food") && player.getInv().isFood() == false) {
                    System.out.println(this.award + " Kazandiniz");
                    player.getInv().setFood(true);
                }else if (this.award.equals("Water") && player.getInv().isWater() == false) {
                    System.out.println(this.award + " Kazandiniz");
                    player.getInv().setWater(true);
                }else if (this.award.equals("Firewood") && player.getInv().isFirewood() == false) {
                    System.out.println(this.award + " Kazandiniz");
                    player.getInv().setFirewood(true);
                }
                return true;    
            }if (player.getHealthy() <= 0){
                System.out.println("ÖLDÜNÜZ ..☠️☠️");
                return false;
            }
        }
        return true;
    }

    public boolean combat(int obsCount) { 
        for (int i = 0 ; i <obsCount;i++) {
            int defObsHealth= obstacle.getHealth();
            playerStats();
            enemyStats();
            while (player.getHealthy() >0 && obstacle.getHealth() >0) {
                System.out.print("<V>ur veya <K>aç : ");
                String selCase=scan.nextLine();
                selCase=selCase.toUpperCase();
                if (selCase.equals("V")) {
                    System.out.println("Siz vurdunuz !");
                    obstacle.setHealth(obstacle.getHealth()-player.getTotalDamage());
                    afterHit();
                    if (obstacle.getHealth() >0){
                        System.out.println();
                        System.out.println("Canavar size vurdu !");
                        player.setHealthy(player.getHealthy()-(obstacle.getDamage()-player.getInv().getArmor()));
                        afterHit();
                    }
                }else{
                    return false;
                }
            }
            if (obstacle.getHealth() < player.getHealthy()  ) {
                System.out.println("Düşmani yendiniz !");
                player.setMoney(player.getMoney() + obstacle.getAward());
                System.out.println("Güncel Paraniz : " + player.getMoney());
                obstacle.setHealth(defObsHealth);


            }else{
                return false;
            }
            System.out.println("-------------------------");
        }
        return true;
    }
    public void playerStats() {
    System.out.println("Oyuncu değerleri\n---------------- ");
    System.out.println("Can: " + player.getHealthy());
    System.out.println("Hasar : " + player.getTotalDamage());
    System.out.println("Para : " + player.getMoney());
    if (player.getInv().getDamage() > 0 ) {
        System.out.println("Silah: " + player.getInv().getwName());
    }
    if (player.getInv().getArmor() > 0 ) {
        System.out.println("Zirh: " +player.getInv().getaName());
    }
    System.out.println();
    }
    
    public void enemyStats() {
        System.out.println(obstacle.getName() + " Değerleri\n--------------");
        System.out.println("Can: "+ obstacle.getHealth());
        System.out.println("Hasar: " + obstacle.getDamage());
        System.out.println("Ödül: " + obstacle.getAward());

    }
    public void afterHit(){
        System.out.println("Oyuncu Cani: " + player.getHealthy());
        System.out.println( obstacle.getName() +" Cani: " + obstacle.getHealth());
        System.out.println();
    }
 
}
