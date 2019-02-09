public class Theoretical  {

    private double prob;

    public Theoretical(int p){
        double num = 365;
        for (int k = 1; k < p; k++) {
            num = num * (365 - k);
        }
        double denom = Math.pow(365, p);
        prob = (1 - num / denom);
    }

    public double getProb(){
        return prob;
    }
}