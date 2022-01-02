package hr.foka.rezijiser.api.common.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.resources.ResourceRequestFilter.FilterOperation;

@Service
public class FilterOperationConverter implements Converter<String, FilterOperation> {

    @Override
    public FilterOperation convert(String source) {
        
        return FilterOperation.valueOf(source);

    }
    
}
