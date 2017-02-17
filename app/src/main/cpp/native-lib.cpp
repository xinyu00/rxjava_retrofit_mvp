//
// Created by tianchen on 2017/2/17.
//
#include <jni.h>
#include <iostream>
#include <map>

using namespace std;

enum StringValue {
    num10,
    num11,
    num12,
    num13,
    num14,
    num16,
    num20,
    num21,
    num22,
    num23,
    num24,
    num26,
    num27,
    num28,
};
map<string, StringValue> mapStringValue;

void setMap() {
    mapStringValue["10"] = num10;
    mapStringValue["11"] = num11;
    mapStringValue["12"] = num12;
    mapStringValue["13"] = num13;
    mapStringValue["14"] = num14;
    mapStringValue["16"] = num16;
    mapStringValue["20"] = num20;
    mapStringValue["21"] = num21;
    mapStringValue["22"] = num22;
    mapStringValue["23"] = num23;
    mapStringValue["24"] = num24;
    mapStringValue["26"] = num26;
    mapStringValue["27"] = num27;
    mapStringValue["28"] = num28;
}


extern "C"
JNIEXPORT jstring JNICALL
Java_com_xy_mvp_utils_JniTest_getStockMarketCode(
        JNIEnv *env,jclass type,
        jstring code/* this */) {
    string stock = env->GetStringUTFChars(code,NULL);
    setMap();
    string backNum = "";
    switch (mapStringValue[stock.substr(0, 2)]) {
        case num10:
            backNum = "1";
            break;
        case num11:
            backNum = "1";
            break;
        case num12:
            backNum = "4";
            break;
        case num13:
            backNum = "1";
            break;
        case num14:
            backNum = "1";
            break;
        case num16:
            backNum = "1";
            break;
        case num20:
            backNum = "2";
            break;
        case num21:
            backNum = "2";
            break;
        case num22:
            backNum = "3";
            break;
        case num23:
            backNum = "2";
            break;
        case num24:
            backNum = "2";
            break;
        case num26:
            backNum = "2";
            break;
        case num27:
            backNum = "2";
            break;
        case num28:
            backNum = "2";
            break;
        default:
            backNum = "";
            break;
    }
    return env->NewStringUTF(backNum.c_str());
}

