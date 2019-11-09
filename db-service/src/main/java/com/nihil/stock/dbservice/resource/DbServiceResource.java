package com.nihil.stock.dbservice.resource;


import com.nihil.stock.dbservice.model.Quote;
import com.nihil.stock.dbservice.model.Quotes;
import com.nihil.stock.dbservice.repository.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

    @Autowired
    private QuotesRepository quotesRepository;

    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable(value = "username")final String username){

        return getQuotesByUsername(username);

    }

    private List<String> getQuotesByUsername(@PathVariable(value = "username") final String username){
        return quotesRepository.findByUserName(username)
                .stream()
                .map(Quote::getQotes)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes){


        quotes.getQuotes()
                .stream()
                .map(quote -> new Quote(quotes.getUserName(),quote))
                .forEach(quote -> quotesRepository.save(quote));

        return getQuotesByUsername(quotes.getUserName());
    }


    @GetMapping("/delete/{username}")
    public List<String> delete(@PathVariable(value = "username") final String username){

        List<Quote> quotes = quotesRepository.findByUserName(username);
        quotesRepository.deleteInBatch(quotes);

        return getQuotesByUsername(username);
    }


}
