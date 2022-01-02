package hr.foka.rezijiser.api.common.converters;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.resources.ResourceFilter.TargetColumn;

@Service
public class TargetColumnConverter implements Converter<String, TargetColumn> {

    @Override
    public TargetColumn convert(String value) {

        for(TargetColumn column : TargetColumn.values()){
            if(column.getColumnName().equalsIgnoreCase(value)){
                return column;
            }
        } 

        throw new EnumConstantNotPresentException(TargetColumn.class, value);
    }
    
}
