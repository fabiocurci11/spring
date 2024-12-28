package faby.faby_BE.dto;

public class ApiResponseDTO {
    private boolean success;
    private String message;

    public ApiResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getter e Setter
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
