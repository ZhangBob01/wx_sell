package com.bob.wx_sell.utils;

import com.bob.wx_sell.VO.ResultVO;

/**
 * @Auther: toudaizhi
 * @Date: 2018-12-22 22:39
 * @Description:
 */
public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }


    public static ResultVO error(Integer code,String message){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(message);
        return  resultVO;
    }
}
