package zonafresnel.controller;

public class CalculoZonaFresnel {
    /*Calculo do Raio*/
     public double calculoRaio(double distancia, double frequencia) {
        double raio = 0;
        raio = (550 * Math.sqrt(((distancia / 2) * (distancia / 2)) / (distancia * frequencia)));
        return (double) raio;
    }
     /*Calculo da atenuacao*/
    public double calculoAtenuacao(double distancia, double frequencia) {
        return (32.44 + 20 * Math.log10(distancia) + 20 * Math.log10(frequencia));
    }
    /*calculo pontencia efetivamente irradianda*/
    public double calculoPotenciaIrradiada(double potenciaTransmissorPx, double ganhoAntenaTx, double atenuacaoConector, double alturaTx, double atenuacaoCabo) {
        double potenciaTransmissorPxdBm = (10 * Math.log10(potenciaTransmissorPx/0.001));
        return potenciaTransmissorPxdBm + ganhoAntenaTx - (2 * atenuacaoConector) - (alturaTx * atenuacaoCabo / 100);
    }
    /*Calculo da Potencia Recebida*/
    public double calculoPotenciaRecebida(double pEirp, double aE, double atenuacaoConector, double alturaRx, double atenuacaoCabo, double ganhoAntenaRx) {
        return pEirp - aE - (2 * atenuacaoConector) - (alturaRx * atenuacaoCabo / 100)+ganhoAntenaRx;
    }
}
