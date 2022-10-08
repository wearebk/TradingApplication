/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.restservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class AppControllerTests {

	public static final String[] anExpected = {
			"Signal: 1setUpsetAlgoParam 1,60performCalcsubmitToMarketdoAlgo",
			"Signal: 2reversesetAlgoParam 1,80submitToMarketdoAlgo",
			"Signal: 3setAlgoParam 1,90setAlgoParam 2,15performCalcsubmitToMarketdoAlgo"
	};

	public static String clean(String s) {
		return s.replace("\n", "").replace("\r", "");
	}

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void signalCorrect() throws Exception {
		// Signals 1..3
		for (int s = 0; s < this.anExpected.length; s++) {
			MvcResult result = this.mockMvc.perform(get("/signal/" + (s + 1)))
					.andDo(print()).andExpect(status().isOk()).andReturn();

			String response = result.getResponse().getContentAsString();
			String actual = JsonPath.parse(response).read("$.output").toString();

			assertEquals(anExpected[s], clean(actual));
		}

		/*this.mockMvc.perform(get("/signal/1"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("1"));*/
	}

	@Test
	public void signalWrong() throws Exception {
		// Signal 4
		String expected = "Signal 4: not implemented.cancelTradesjava.lang.ClassNotFoundException: com.example.restservice.Sig4";

		MvcResult result = this.mockMvc.perform(get("/signal/4"))
				.andDo(print()).andExpect(status().isOk()).andReturn();

		String response = result.getResponse().getContentAsString();
		String actual = JsonPath.parse(response).read("$.output").toString();

		assertEquals(expected, clean(actual));
	}
}
