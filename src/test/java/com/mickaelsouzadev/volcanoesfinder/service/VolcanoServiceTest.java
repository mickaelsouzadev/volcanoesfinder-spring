package com.mickaelsouzadev.volcanoesfinder.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mickaelsouzadev.volcanoesfinder.model.Volcano;
import com.mickaelsouzadev.volcanoesfinder.service.http.GlobalVolcanismService;

public class VolcanoServiceTest {
    private GlobalVolcanismService globalVolcanismService;
    private VolcanoService volcanoService;

    @BeforeEach
    public void setUp() {
        globalVolcanismService = mock(GlobalVolcanismService.class);
        volcanoService = new VolcanoService(globalVolcanismService);
    }

    @Test
    public void testListVolcanoes() {
        String html = """
                    <div class="TableSearchResults">
                        <table>
                            <tr>
                                <td>Krakatoa</td>
                                <td>Sunda Strait</td>
                                <td>Stratovolcano</td>
                            </tr>
                        </table>
                    </div>
                """;

        when(globalVolcanismService.getHoloceneVolcanoesList()).thenReturn(html);

        List<Volcano> volcanoes = volcanoService.listVolcanoes();

        assertFalse(volcanoes.isEmpty());
        assertEquals(1, volcanoes.size());
        assertEquals("Krakatoa", volcanoes.get(0).getName());
        assertEquals("Sunda Strait", volcanoes.get(0).getLocation());
        assertEquals("Stratovolcano", volcanoes.get(0).getType());
    }
}
