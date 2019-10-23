package Tools;

import DataModel.AccountModel;
import Gui.Gui;
import org.apache.commons.codec.binary.Base32;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Tools {

    private static final Path path = Paths.get(System.getProperty("user.home"), ".adi_config");

    public static void loadAccountFromSystem() {
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader(Tools.path.toString())) {
            JSONArray accountList = (JSONArray)jsonParser.parse(reader);
            for (int i = 0; i < accountList.size(); i++) {
                JSONObject tmp_obj = (JSONObject) accountList.get(i);
                AccountModel tmp_account = new AccountModel((String)tmp_obj.get("email"), (String)tmp_obj.get("description"), (String)tmp_obj.get("token"));
                Gui.getAccountModelList().addElement(tmp_account);
            }
            Gui.getAccountModelJList().updateUI();
            Tools.updateOTP();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void saveAccountInSystem() {
        JSONArray accountList = new JSONArray();

        for (int i = 0; i < Gui.getAccountModelList().size(); i++) {
            JSONObject tmp = new JSONObject();

            AccountModel tmp_model = Gui.getAccountModelList().get(i);
            tmp.put("description", tmp_model.getDescription());
            tmp.put("email", tmp_model.getAccount());
            tmp.put("token", tmp_model.getPrivateKey());

            accountList.add(tmp);
        }
        try (FileWriter file = new FileWriter(Tools.path.toString())) {
            file.write(accountList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateOTP() {
        for (int i = 0; i < Gui.getAccountModelList().size(); i++) {
            try {
                Gui.getAccountModelList().get(i).setOtp(Algo.compute_otp(new Base32().decode(Gui.getAccountModelList().get(i).getPrivateKey()), Algo.get_cTime()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Gui.getAccountModelJList().updateUI();
    }

}
