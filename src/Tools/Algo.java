package Tools;

import Gui.Gui;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Algo {

    public Algo() {
        time_updater();
    }

    public static String compute_otp(byte[] key, long tm) throws NoSuchAlgorithmException, InvalidKeyException {
        // Allocation d'un ByteBuffer pour time
        ByteBuffer time_tmp = ByteBuffer.allocate(8);

        // Convertion du temps(long -> byteBuffer)
        time_tmp.putLong(tm);

        // Convertion du temps vers un unsigned int Ref. RFC4226
        byte[] time = time_tmp.array();

        // Creation de la clé spécifique pour l'algorithme HmacSHA1
        SecretKeySpec hmac_key = new SecretKeySpec(key, "HmacSHA1");

        // Initialisation de l'algorithme HMACSHA1
        Mac mac = Mac.getInstance("HmacSHA1");

        // Initialisation de l'instance avec la clé d'algo
        mac.init(hmac_key);

        //Finalisation de l'instance avec la variable temps/ recuperation de la donnée chiffrée
        byte[] hash = mac.doFinal(time);

        // Generation d'une chaine de 4octets (dynamic troncature) ref RFC4226
        int offset = hash[hash.length - 1] & 0xF;

        // Utilisation d'un long en remplacement d'un unsigned int(pas de type unsigned en java)
        // et nous avons besoin de 32bits
        long result = ((hash[offset] & 0x7f) << 24) | ((hash[offset + 1] & 0xff) << 16) | ((hash[offset + 2] & 0xff) << 8) | (hash[offset + 3] & 0xff);

        // Creation du code à 6 chiffres
        result %= (int) Math.pow(10, 6);
        return String.format("%06d", result);
    }

    public static long get_cTime() {
        long time = new Date().getTime() / TimeUnit.SECONDS.toMillis(30);
        return time;
    }

    private void time_updater() {
        new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Date tmp_date = new Date();
                int seconds = tmp_date.getSeconds();
                if (seconds == 0 || seconds == 30) {
                    Tools.updateOTP();
                }
                Gui.update_time(tmp_date);
            }
        }).start();
    }

}
