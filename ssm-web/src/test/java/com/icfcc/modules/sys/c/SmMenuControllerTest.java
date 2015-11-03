package com.icfcc.modules.sys.c;

import com.icfcc.TestBase;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

/**
 * Created by admin on 2015/11/3.
 */
public class SmMenuControllerTest extends TestBase {
    @Test
    public void hellowordtestJSON() throws Exception {
        ResultActions ra = mockMvc.perform(MockMvcRequestBuilders.get("/sm/menu/usermenus.json"));
        ra.andDo(MockMvcResultHandlers.print());
        MvcResult result = ra.andReturn();
//                mockMvc.perform(MockMvcRequestBuilders.get("/helloword"))
//                .andExpect(MockMvcResultMatchers.view().name("sample/helloword"))
////                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
    }
}
