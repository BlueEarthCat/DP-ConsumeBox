package com.blueearthcat.dpcb.box.enums;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public enum BoxType {
    RANDOM,
    SELECT,
    GIFT,
    ERROR;

    @NotNull
    public static BoxType fromString(@NotNull String str){
        try {
            return BoxType.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e){
            return ERROR;
        }
    }

    @NotNull
    public static List<String> getTypes(){
        List<String> types = new ArrayList<>();
        for(BoxType type : values()){
            if(type == ERROR) continue;
            types.add(type.name());
        }
        return types;
    }
}
