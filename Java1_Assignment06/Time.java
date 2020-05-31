/*  CS209A_JAVA2 
    Copyright (C) 2020 nanoseeds
    
    CS209A_JAVA2 is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    CS209A_JAVA2 is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
    */

/**
 * @Github: https://github.com/Certseeds/CS209A_JAVA2
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-05-31 20:19:43
 * @LastEditors : nanoseeds
 */

public class Time implements Comparable<Time> {
    private int hour;
    private int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public String toString() {
        String h = String.format("%s%s", this.hour <= 9 ? "0" : "", String.valueOf(this.hour));
        String m = String.format("%s%s", this.minute <= 9 ? "0" : "", String.valueOf(this.minute));
        return String.format("%s:%s", h, m);
    }

    public static int minus(Time t1, Time t2) {
        return (t1.hour * 60 + t1.minute) -
                (t2.hour * 60 + t2.minute);
    }

    @Override
    public int compareTo(Time o) {
        int count = minus(this, o);
        if (count > 0) {
            return 1;
        } else if (count < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
