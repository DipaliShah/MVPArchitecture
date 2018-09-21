
package com.dipalimvpsample.mvparchitecture.main;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class SampleDto {

    @SerializedName("age")
    private Long mAge;
    @SerializedName("name")
    private String mName;

    public Long getAge() {
        return mAge;
    }

    public void setAge(Long age) {
        mAge = age;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
