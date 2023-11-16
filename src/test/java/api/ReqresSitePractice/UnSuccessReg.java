package api.ReqresSitePractice;

public class UnSuccessReg {
    public String errorMessage;

    public UnSuccessReg(){
    }

    public UnSuccessReg( String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public String getErrorMessage() {
        return errorMessage;
    }
}
