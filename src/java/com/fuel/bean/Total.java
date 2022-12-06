//used to get the request count, total

package com.fuel.bean;

public class Total {
    private String count;
    private String sums;

    public Total(String count, String sums) {
        this.count = count;
        this.sums = sums;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSums() {
        return sums;
    }

    public void setSums(String sums) {
        this.sums = sums;
    }
    
    
}
