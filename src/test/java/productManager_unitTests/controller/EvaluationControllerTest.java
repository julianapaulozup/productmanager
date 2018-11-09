package productManager_unitTests.controller;

import net.minidev.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import productManager_unitTests.service.evaluation.Evaluation;
import productManager_unitTests.service.evaluation.EvaluationService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(EvaluationController.class)
@AutoConfigureDataJpa
public class EvaluationControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EvaluationService service;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void givenProducts_whenGetProducts_thenReturnJsonArray() throws Exception {


        Evaluation evaluation= new Evaluation(11L,"Comentário",8);

        List<Evaluation> allEvaluations = Arrays.asList(evaluation);

        given(service.getAllEvaluations()).willReturn(allEvaluations);

        mvc.perform(get("/evaluations/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].commentary").value("Comentário"));
    }

    @Test
    public void whenpostProduct_thenReturnCreated() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("commentary", "Comentário");
        jsonObject.put("score", 10.0);
        mvc.perform(post("/evaluations/")
                .contentType("application/json;charset=UTF-8")
                .content(String.valueOf(jsonObject)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void whenputProduct_thenReturnOk() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("commentary", "Comentário");
        jsonObject.put("score", 10.0);
        mvc.perform(put("/evaluations/{id}", 1L)
                .contentType("application/json;charset=UTF-8")
                .content(String.valueOf(jsonObject)))
                .andExpect(status().isOk());
    }

    @Test
    public void whendeleteProduct_thenReturnOk() throws Exception {

        mvc.perform(delete("/evaluations/{id}", 1l))
                .andExpect(status().isOk());
    }

}
