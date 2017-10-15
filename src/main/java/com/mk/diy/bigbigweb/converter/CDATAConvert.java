package com.mk.diy.bigbigweb.converter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CDATAConvert implements Converter {

	/**
     * java对象转换为xml
	 */
	public void marshal(Object object, HierarchicalStreamWriter writer,
			MarshallingContext context) {

		String prefix = "<![CDATA[";
		String suffix = "]]>";
		String trans = prefix + object + suffix;
		writer.setValue(trans);
	}

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        return hierarchicalStreamReader.getValue();
    }

	/**
	 * 判断字段是否是需要转换的类型
	 */
	public boolean canConvert(Class paramClass) {
		return String.class.isAssignableFrom(paramClass);
	}

}