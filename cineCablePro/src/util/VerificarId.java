package util;

import exceptions.VerificarIdException;

/**
*
* @author clainez
* @version 1.1
* 22/03/2011
*/
public class VerificarId {

   private final int INT_NUMPROECU = 24;
   private final int INT_NUMDIGCED = 10;
   private final int INT_NUMDIGRUC = 13;

   public boolean verificarId(String strId) throws VerificarIdException{
       boolean blnOk=false;

       if(strId.length()==INT_NUMDIGCED){
           blnOk=verificarCed(strId);
       }else{
           if(strId.length()==INT_NUMDIGRUC){
               int intTerDig = Integer.parseInt(strId.substring(2, 3));
               if(intTerDig<6){
                   blnOk=verificarRucPerNat(strId);
               }else{
                   if(intTerDig==6){
                       blnOk=verificarRucInsPub(strId);
                   }else{
                       if(intTerDig==9){
                           blnOk=verificarRucJurExtSinCed(strId);
                       }
                   }
               }
           }else{
               throw new VerificarIdException("Longitud de n�mero de identificaci�n incorrecto.");
           }
       }

       return blnOk;
   }

   /**
    * Algoritmo "Módulo 10" valida cédula de identidad de una Persona según dígito verificador.
    *
    * @param strCed Cédula de Identidad
    * @return Retorna true si es correcto o false si no lo es
    * @throws VerificarIdException
    */
   private boolean verificarCed(String strCed) throws VerificarIdException {
       boolean blnOk = (strCed.length()==INT_NUMDIGCED);

       if(blnOk){
           blnOk = (Integer.parseInt(strCed.substring(0, 2)) > 0
                   && Integer.parseInt(strCed.substring(0, 2)) <= INT_NUMPROECU
                   && Integer.parseInt(strCed.substring(2, 3)) < 6);

           if (blnOk) {
               int intSum = 0;
               int[] intCoe = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };

               for (int i = 0; i <= 8; i++) {
                   int intTot = Integer.parseInt(strCed.substring(i, i + 1)) * intCoe[i];

                   if (intTot > 9){
                       intTot -= 9;
                   }

                   intSum += intTot;
               }

               int intRes = intSum % 10;
               int intDigVer = intRes>0?(10-intRes):0;

               blnOk = (intDigVer == Integer.parseInt(strCed.substring(9, 10)));
               if (!blnOk){
                   throw new VerificarIdException("N�mero de identificaci�n incorrecto.");
               }
           } else{
               throw new VerificarIdException("N�mero de identificaci�n incorrecto");
           }
       }else{
           throw new VerificarIdException("Longitud de n�mero de identificaci�n incorrecto.");
       }

       return blnOk;
   }

   /**
    * Algoritmo "Módulo 10" valida RUC de Personas Naturales por dígito verificador
    *
    * @param strRuc RUC de Persona Natural
    * @return Retrona true si es correcto y false si no lo es
    * @throws VerificarIdException
    */
   private boolean verificarRucPerNat(String strRuc) throws VerificarIdException {
       boolean blnOk = (strRuc.length()==INT_NUMDIGRUC);

       if(blnOk){
           try {
               blnOk = verificarCed(strRuc.substring(0, 10));

               if(!(blnOk && Integer.parseInt(strRuc.substring(10, 13)) > 0)){
                   throw new VerificarIdException("RUC incorrecto");
               }
           } catch (VerificarIdException ex) {
               throw new VerificarIdException(ex.getMessage());
           }
       }else{
           throw new VerificarIdException("Longitud del RUC incorrecto.");
       }

       return blnOk;
   }

   /**
    * Algoritmo "Módulo 11" valida RUC de Personas Jurídicas y Extranjeras sin Cédula por dígito verificador
    *
    * @param strRuc RUC de Persona Jurídica y Extranjera
    * @return Retorna true si es correcto y false si no lo es
    * @throws VerificarIdException
    */
   private boolean verificarRucJurExtSinCed(String strRuc) throws VerificarIdException {
       boolean blnOk = (strRuc.length()==INT_NUMDIGRUC);

       if(blnOk){
           blnOk = (Integer.parseInt(strRuc.substring(0, 2)) > 0
                   && Integer.parseInt(strRuc.substring(0, 2)) <= INT_NUMPROECU
                   && Integer.parseInt(strRuc.substring(2, 3)) == 9
                   && Integer.parseInt(strRuc.substring(10, 13)) > 0);

           if(blnOk) {
               int[] intCoe = { 4, 3, 2, 7, 6, 5, 4, 3, 2 };
               int intSum = 0;
               
               for (int i = 0; i <= 8; i++){
                   intSum += Integer.parseInt(strRuc.substring(i, i + 1)) * intCoe[i];
               }

               int intRes = intSum % 11;
               int intDigVer = intRes>0?(11-intRes):0;

               blnOk = (intDigVer == Integer.parseInt(strRuc.substring(9, 10)));
               
               if (!blnOk){
                   throw new VerificarIdException("RUC incorrecto.");
               }
           }else{
               throw new VerificarIdException("RUC incorrecto.");
           }
       }else{
           throw new VerificarIdException("Longitud del RUC incorrecto.");
       }

       return blnOk;
   }

   /**
    * Algoritmo "Módulo 11" valida RUC de Instituciones Públicas según dígito verificador
    *
    * @param strRuc RUC de Instituciones Públicas
    * @return Retorna true si es correcto y false si no lo es
    * @throws Exception
    */
   private boolean verificarRucInsPub(String strRuc) throws VerificarIdException {
       boolean blnOk = (strRuc.length()==INT_NUMDIGRUC);

       if(blnOk){
           blnOk = (Integer.parseInt(strRuc.substring(0, 2)) > 0
                   && Integer.parseInt(strRuc.substring(0, 2)) <= INT_NUMPROECU
                   && Integer.parseInt(strRuc.substring(2, 3)) == 6
                   && Integer.parseInt(strRuc.substring(9, 13)) > 0);

           if (blnOk) {
               int[] intCoe = { 3, 2, 7, 6, 5, 4, 3, 2 };
               int intSum = 0;
                       
               for (int i = 0; i <= 7; i++){
                   intSum += Integer.parseInt(strRuc.substring(i, i + 1)) * intCoe[i];
               }
                   
               int intRes = intSum % 11;
               int intDigVer = intRes>0?(11-intRes):0;

               blnOk = (intDigVer == Integer.parseInt(strRuc.substring(8, 9)));

               if(!blnOk){
                   throw new VerificarIdException("RUC incorrecto.");
               }
           } else{
               throw new VerificarIdException("RUC incorrecto.");
           }
       }else{
           throw new VerificarIdException("Longitud del RUC incorrecto");
       }

       return blnOk;
   }

}
