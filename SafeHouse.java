public class SafeHouse extends NormalLoc {

    SafeHouse(Player player) {
        super(player,"Güvenli Ev");    
    }
    public boolean getLocation() {
        player.setHealthy(player.getrHealthy());
        System.out.println("İyleştiniz...");
        System.out.println("Şuan güvenli ev adli yerdesiniz.");
        return true;
    }   
}
