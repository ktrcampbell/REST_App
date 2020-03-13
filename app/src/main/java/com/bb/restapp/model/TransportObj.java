package com.bb.restapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class TransportObj implements Parcelable {

    private List<GitResult> gitResults;

    public TransportObj(List<GitResult> gitResults) {
        this.gitResults = gitResults;
    }

    protected TransportObj(Parcel in) {
    }

    public static final Creator<TransportObj> CREATOR = new Creator<TransportObj>() {
        @Override
        public TransportObj createFromParcel(Parcel in) {
            return new TransportObj(in);
        }

        @Override
        public TransportObj[] newArray(int size) {
            return new TransportObj[size];
        }
    };

    public List<GitResult> getGitResults() {
        return gitResults;
    }

    public void setGitResults(List<GitResult> gitResults) {
        this.gitResults = gitResults;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
