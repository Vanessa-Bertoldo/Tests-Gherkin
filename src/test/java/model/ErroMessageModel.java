package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class ErroMessageModel {
    @Expose
    private String message;
}
