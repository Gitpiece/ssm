package com.icfcc.web.view.xml;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;
import org.springframework.web.servlet.view.xml.MarshallingView;

/*
 * View resolver for returning XML in a view-based system. Always returns a
 * {@link MarshallingView}.
 */
public class MarshallingXmlViewResolver implements ViewResolver {

    @Autowired(required = false)
    private Marshaller marshaller;

    public MarshallingXmlViewResolver(){

    }
    public MarshallingXmlViewResolver(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    /**
     * Get the view to use.
     *
     * @return Always returns an instance of {@link MappingJackson2JsonView}.
     */
    @Override
    public View resolveViewName(String viewName, Locale locale)
            throws Exception {
        View view = null;
        if(marshaller != null){
            MarshallingView marshallingView = new MarshallingView();
            marshallingView.setMarshaller(marshaller);
            view = marshallingView;
        }
        else{
            view = new MappingJackson2XmlView();
        }
        return view;
    }

}