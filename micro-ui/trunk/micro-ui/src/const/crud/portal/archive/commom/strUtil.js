import { validatenull } from "@/util/validate";

/**
 * 是否包含某个字符串
 * */
export function  containStr(str,val,val2,val3){
    if(!validatenull(str)&&((str.trim()).indexOf(val)==0||(str.trim()).indexOf(val2)==0||(str.trim()).indexOf(val3)==0)){
        return true
    }else{
        return false
    }
}














