package zonafresnel.controller;

public class CalculoZonaFresnel {
     public double calculaRaio(double distancia, double frequencia) {
        double raio = 0;

        raio = (550 * Math.sqrt(((distancia / 2) * (distancia / 2)) / (distancia * frequencia)));

        return (double) raio;
    }

    public double calculaAe(double distancia, double frequencia) {
        System.out.println(32.44 + 20 * Math.log10(distancia) + 20 * Math.log10(frequencia));
        return (32.44 + 20 * Math.log10(distancia) + 20 * Math.log10(frequencia));
    }
    
    public double calculaPotenciaEfetivamenteIrradiadaPeirp (double potenciaTransmissorPx, double ganhoAntenaTx, double atenuacaoConector, double alturaTx, double atenuacaoCabo) {
        double potenciaTransmissorPxdBm = (10 * Math.log10(potenciaTransmissorPx/0.001));
        return potenciaTransmissorPxdBm + ganhoAntenaTx - (2 * atenuacaoConector) - (alturaTx * atenuacaoCabo / 100);
    }
    
    public double calculaPotenciaRecebidaPr (double pEirp, double aE, double atenuacaoConector, double alturaRx, double atenuacaoCabo) {
        return pEirp - aE - (2 * atenuacaoConector) - (alturaRx * atenuacaoCabo / 100);
    }
}
