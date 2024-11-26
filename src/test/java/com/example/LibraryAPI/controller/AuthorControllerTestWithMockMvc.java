//package com.example.LibraryAPI.controller;
//
//import com.example.LibraryAPI.configs.ApplicationConfiguration;
//import com.example.LibraryAPI.repository.AuthorRepository;
//import com.example.LibraryAPI.repository.UserRepository;
//import io.restassured.RestAssured;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static common.Common.*;
//import static net.minidev.asm.BeansAccess.get;
//import static org.hamcrest.Matchers.equalTo;
//import static org.hamcrest.Matchers.hasSize;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//
//public class AuthorControllerTestWithMockMvc {
//
////    @LocalServerPort
////    private int port;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Autowired
//    private AuthorRepository authorRepository;
//
//
//
//    private MockMvc mockMvc;
//    @BeforeEach
//    public void setup() throws Exception {
//
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//
//    public  String getTokenBearer(String email, String password) throws Exception{
//
//        String requestBody= "{\"email\":\""+email+"\",\"password\":\""+password+"\"}";
//
//        MvcResult mvcResult = mockMvc.perform(post( AuthenticationController.baseControllerUri+AuthenticationController.loginUri)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody))
//                                  .andExpect(status().isOk())
//                .andExpect(jsonPath("$.token").exists())
//                .andReturn();
//        return  mvcResult.toString();
//
//    }
//
//    @Test
//    void getAllAuthors() throws Exception{
//
//        String token= getTokenBearer(userEmail, userPassword);
//
//        var size =authorRepository.findAll().size();
//        MvcResult mvcResult = (MvcResult) this.mockMvc.perform(MockMvcRequestBuilders
//                        .get(AuthorController.baseControllerUri+AuthorController.getAllAuthorsUri)
//                        .header("Authorization","Bearer "+token))
//                         .andDo(print()).andExpect(status().isOk())
//                        .andExpect(jsonPath("$", hasSize(size)));
//    }
//
//    @Test
//    void getAuthor() {
//    }
//
//    @Test
//    void updateAuthor() {
//    }
//
//    @Test
//    void createAuthor() {
//    }
//
//    @Test
//    void deleteAuthor() {
//    }
//
//    @Test
//    void testGetAuthor() {
//    }
//}