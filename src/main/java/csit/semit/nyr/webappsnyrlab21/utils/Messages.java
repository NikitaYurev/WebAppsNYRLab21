package csit.semit.nyr.webappsnyrlab21.utils;

public enum Messages {

    OK_INS_MSG ("CLIENT insert -- SUCCESS!"),
    OK_UPD_MSG  ("CLIENT update -- SUCCESS!"),
    OK_DEL_MSG  ("CLIENT delete -- SUCCESS!"),
    ERR_INS_MSG ("CLIENT insert -- ERROR!"),

    ERR_UPD_MSG ("CLIENT update -- ERROR!"),

    ERR_NOT_IN_MSG ("ERROR -- CLIENT not found in DB!"),

    ERR_DOUBLE_MSG ("ERROR -- CLIENT already present in DB!"),

    //Message for check validation
    OK_NBN_VALID_MSG ("HBN Validation -- SUCCESS!"),
    ERR_NBN_VALID_MSG ("ERROR -- HBN Validation violation!");


    private String text;

    Messages(String s) {
        text = s;
    }

    public String getText() {
        return text;
    }

    public static Messages getMessageByText(String text) {
        int index = -1;
        Messages[] messagesValues = values();
        boolean flFound = false;
        while (!flFound && index<messagesValues.length-1) {
            index++;
            if (messagesValues[index].getText().equals(text)) {
                flFound = true;
            }
        }
        Messages msg = null;
        if (!flFound) {
            msg = null;
        } else {
            msg = Messages.values()[index];
        }
        return msg;
    }

}
