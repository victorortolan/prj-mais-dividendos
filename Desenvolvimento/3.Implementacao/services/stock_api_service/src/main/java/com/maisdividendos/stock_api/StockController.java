package com.maisdividendos.stock_api;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.maisdividendos.stock_api.entities.StockPrice;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    // Receber StockRequest e entregar StockReponse //dto
    @GetMapping("/{ticker}")
    public ResponseEntity<StockPrice> getPriceByTicker(@PathVariable String ticker) {
        System.out.println(ticker);
        StockPrice response = stockService.getPriceByTicker(ticker);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(response);
    }// nao pode retornar dois resultados, nem ter no banco
     // fazer tratamento de erros nos endpoints

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/prices")
    public ResponseEntity<Map<String, List<StockPrice>>> getPrices() {
        List<StockPrice> serviceResponse = stockService.getPrices();
        Map<String, List<StockPrice>> response = new HashMap<>();
        response.put("prices", serviceResponse);
        System.out.println(response);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(response);
    }
    // git checkout refactor/stock-api
    // -> criar a entity lista de stockprices (ver conversa no gpt)
    // -> finalizar endpoint /prices
    // -> apagar pastas e tabelas que ainda nao estao sendo usadas
    // -> organizar controller/ model/entities/ model/dtos model/services/
    // model/repository/
    // git checkout refactor/frontend
    // -> refatorar o frontend para chamar a api e mainpular dados
    // intergração com a brapi para pegar a percentagem
}
