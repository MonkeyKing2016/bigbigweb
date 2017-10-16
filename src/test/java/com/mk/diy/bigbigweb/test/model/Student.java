package com.mk.diy.bigbigweb.test.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("student")    //define class level alias
public class Student {

    
    @XStreamAlias("name")   //define field level alias
    @XStreamAsAttribute     //define field as attribute
	private String studentName;
	
	@XStreamImplicit        //define list as an implicit collection
	private List<TestXmlModel> notes = new ArrayList<TestXmlModel>();
	
	@XStreamOmitField        //omit a field to not to be a part of XML
	private int type;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<TestXmlModel> getNotes() {
        return notes;
    }

    public void setNotes(List<TestXmlModel> notes) {
        this.notes = notes;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}