package cn.edu.njust.dev.ses.main.dto;

import lombok.Data;

@Data
public class ResultDTO<T> {
    private Integer code;
    private String message;
    private Long count;
    private String detailedMessage;
    private T data;

    public static ResultDTO errorOf(Integer code, String message) {
        return errorOf(code, message, "");
    }

    public static ResultDTO errorOf(Integer code, String message, String details) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        resultDTO.setDetailedMessage(details);
        return resultDTO;
    }

    public static ResultDTO okOf() {
        return okOf(null, null);
    }

    public static <T> ResultDTO okOf(T t) {
        return okOf(t, null);
    }

    public static <T> ResultDTO okOf(T t, Long count){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        resultDTO.setData(t);
        resultDTO.setCount(count);
        return resultDTO;
    }
}
