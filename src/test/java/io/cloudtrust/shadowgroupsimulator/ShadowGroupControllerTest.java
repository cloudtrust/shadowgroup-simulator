package io.cloudtrust.shadowgroupsimulator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Test class for the ShadowGroupController. Uses the @WebMvcTest annotation... so practical and simple.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ShadowGroupController.class)
public class ShadowGroupControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetShadowGroupFullOk() throws Exception {
        mvc.perform(get("/shadowgroups/usg/add").header("referer", "testReferer")
                .header("User-Agent", "testAgent")
                .param("applicationUrl", "test.application.com")
                .param("mobilityStatus", "mobile")
                .param("jobFunctionCode", "12345"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.$id", is(1)))
                .andExpect(jsonPath("$.$values.length()", is(7)))
                .andExpect(jsonPath("$.$values[0]", is("alpha")))
                .andExpect(jsonPath("$.$values[1]", is("bravo")))
                .andExpect(jsonPath("$.$values[2]", is("charlie")))
                .andExpect(jsonPath("$.$values[3]", is("delta")))
                .andExpect(jsonPath("$.$values[4]", is("add")))
                .andExpect(jsonPath("$.$values[5]", is("test.application.com")))
                .andExpect(jsonPath("$.$values[6]", is("mobile")));
    }

    @Test
    public void testGetShadowGroupMinimalOk() throws Exception {
        mvc.perform(get("/shadowgroups/usg/add").header("referer", "testReferer")
                .header("User-Agent", "testAgent"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.$id", is(1)))
                .andExpect(jsonPath("$.$values.length()", is(5)))
                .andExpect(jsonPath("$.$values[0]", is("alpha")))
                .andExpect(jsonPath("$.$values[1]", is("bravo")))
                .andExpect(jsonPath("$.$values[2]", is("charlie")))
                .andExpect(jsonPath("$.$values[3]", is("delta")))
                .andExpect(jsonPath("$.$values[4]", is("add")));
    }

    @Test
    public void testGetShadowGroupMissingUserAgent() throws Exception {
        mvc.perform(get("/shadowgroups/usg/add").header("referer", "testReferer")
                .param("applicationUrl", "test.application.com")
                .param("mobilityStatus", "mobile")
                .param("jobFunctionCode", "12345"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetShadowGroupMissingUserAgentAndCheckAgent() throws Exception {
        mvc.perform(get("/shadowgroups/usg/checkuseragent").header("referer", "testReferer")
                .param("applicationUrl", "test.application.com")
                .param("mobilityStatus", "mobile")
                .param("jobFunctionCode", "12345"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetShadowGroupMissingReferer() throws Exception {
        mvc.perform(get("/shadowgroups/usg/add")
                .param("applicationUrl", "test.application.com")
                .param("mobilityStatus", "mobile")
                .param("jobFunctionCode", "12345"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetShadowGroupNotFound() throws Exception {
        mvc.perform(get("/shadowgroups/usg/notfound")
                .header("referer", "testReferer")
                .param("applicationUrl", "test.application.com")
                .param("mobilityStatus", "mobile")
                .param("jobFunctionCode", "12345"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetShadowGroupNotError() throws Exception {
        mvc.perform(get("/shadowgroups/usg/error")
                .header("referer", "testReferer")
                .param("applicationUrl", "test.application.com")
                .param("mobilityStatus", "mobile")
                .param("jobFunctionCode", "12345"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetShadowGroupNoUsername() throws Exception {
        mvc.perform(get("/shadowgroups/usg/")
                .header("referer", "testReferer")
                .param("applicationUrl", "test.application.com")
                .param("mobilityStatus", "mobile")
                .param("jobFunctionCode", "12345"))
                .andExpect(status().isNotFound());
    }
}
