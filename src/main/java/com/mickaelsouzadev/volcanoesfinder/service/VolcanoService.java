package com.mickaelsouzadev.volcanoesfinder.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.mickaelsouzadev.volcanoesfinder.model.Volcano;
import com.mickaelsouzadev.volcanoesfinder.service.http.GlobalVolcanismService;

@Service
public class VolcanoService {
    private GlobalVolcanismService globalVolcanismService;

    public VolcanoService(GlobalVolcanismService globalVolcanismService) {
        this.globalVolcanismService = globalVolcanismService;
    }

    public List<Volcano> getFormattedVolcanoes() {
        List<List<String>> volcanoes = this.getVolcanoesFromWeb();

        return volcanoes.stream()
                .filter(volcano -> !volcano.isEmpty())
                .map(volcano -> new Volcano(volcano.get(0), volcano.get(1), volcano.get(2)))
                .toList();
    }

    private List<List<String>> getVolcanoesFromWeb() {
        String html = globalVolcanismService.getHoloceneVolcanoesList();
        Document doc = Jsoup.parse(html);

        Elements rows = doc.select("div.TableSearchResults table tr");

        List<List<String>> volcanoes = new ArrayList<>();

        for (Element row : rows) {
            Elements cells = row.select("td");

            if (!cells.isEmpty()) {
                List<String> rowData = new ArrayList<>();
                for (Element cell : cells) {
                    rowData.add(cell.text().trim());
                }
                volcanoes.add(rowData);
            }
        }

        return volcanoes;
    }
}
