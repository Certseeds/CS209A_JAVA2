/*  Java2RESTfulCorpusPlatform-Demo 
    Copyright (C) 2020 nanoseeds
    
    Java2RESTfulCorpusPlatform-Demo is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    Java2RESTfulCorpusPlatform-Demo is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
    */
/**
 * @Github: https://github.com/Certseeds/Java2RESTfulCorpusPlatform-Demo
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-05-21 21:06:56
 * @LastEditors : nanoseeds
 */
package model;

/**
 * The type Summary.
 * This class mainly use during Serialization in the list file part.
 */
public class summary {
    /**
     * The Md 5.
     */
    String md5;
    /**
     * The Length.
     */
    int length;
    /**
     * The Preview.
     */
    String preview;

    /**
     * Instantiates a new Summary.
     *
     * @param md5     the md 5
     * @param length  the length
     * @param preview the preview
     */
    public summary(String md5, int length, String preview) {
        this.md5 = md5;
        this.length = length;
        this.preview = preview;
    }

    /**
     * Gets md 5.
     *
     * @return the md 5
     */
    public String getMd5() {
        return md5;
    }

    /**
     * Sets md 5.
     *
     * @param md5 the md 5
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     * Gets length.
     *
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets length.
     *
     * @param length the length
     */
    private void setLength(int length) {
        this.length = length;
    }

    /**
     * Gets preview.
     *
     * @return the preview
     */
    public String getPreview() {
        return preview;
    }

    /**
     * Sets preview.
     *
     * @param preview the preview
     */
    private void setPreview(String preview) {
        this.preview = preview;
    }

    @Override
    public String toString() {
        return "summary {" +
                "md5='" + this.getMd5() + '\'' +
                ", length=" + this.getLength() + '\'' +
                ", preview=" + this.getPreview() + '}';

    }
}
