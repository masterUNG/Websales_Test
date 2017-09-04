package pneumax.websales;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sitrach on 02/09/2017.
 */

class Employees implements Parcelable {
    public String STFcode;
    public String STFfullname;
    public String DPcode;
    public String BRcode;
    public String STFstart;
    public String STFend;

    public final static String TABLE_NAME = "Employees";
    public final static String COLUMN_STFCODE = "STFCODE";
    public final static String COLUMN_STFFULLNAME = "STFFULLNAME";
    public final static String COLUMN_DPCODE = "DPCODE";
    public final static String COLUMN_BRCODE = "BRCODE";
    public final static String COLUMN_STFSTART = "START";
    public final static String COLUMN_STFEND = "STFEND";

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.STFcode);
        dest.writeString(this.STFfullname);
        dest.writeValue(this.DPcode);
        dest.writeValue(this.BRcode);
        dest.writeValue(this.STFstart);
        dest.writeValue(this.STFend);
    }

    public Employees() {
    }

    protected Employees(Parcel in) {
        this.STFcode = in.readString();
        this.STFfullname = in.readString();
        this.DPcode = in.readString();
        this.BRcode = in.readString();
        this.STFstart = in.readString();
        this.STFend = in.readString();
    }

    public static final Parcelable.Creator<Employees> CREATOR = new Parcelable.Creator<Employees>() {
        @Override
        public Employees createFromParcel(Parcel source) {
            return new Employees(source);
        }

        @Override
        public Employees[] newArray(int size) {
            return new Employees[size];
        }
    };
}
