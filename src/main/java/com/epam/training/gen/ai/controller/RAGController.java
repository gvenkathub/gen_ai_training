package com.epam.training.gen.ai.controller;

import com.epam.training.gen.ai.modal.Hotel;
import com.epam.training.gen.ai.service.RAGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RAGController {

        @Autowired
        private RAGService ragService;

        /**
         * Creates an embedding for the given content.
         *
         * @param embeddingContent the content to create an embedding for
         * @return a ResponseEntity containing the result of the embedding creation
         */
        @PostMapping("/create-embedding")
        public ResponseEntity<String> createEmbedding(@RequestParam String embeddingContent) {
            return ResponseEntity.ok(ragService.createEmbedding(embeddingContent));
        }

        /**
         * Loads the embedding data.
         *
         * @return a ResponseEntity indicating that the data has been loaded
         */
        @PostMapping("/load-embedding")
        public ResponseEntity<String> loadEmbedding() {
            ragService.loadData();
            return ResponseEntity.accepted().build();
        }

        /**
         * Searches for similar hotels based on the given query.
         *
         * @param query the search query
         * @return a ResponseEntity containing the result of the search
         */
        @GetMapping("/search")
        public ResponseEntity<Hotel> searchSimilar(
                @RequestParam String query) {
            return ResponseEntity.ok(ragService.search(query));
        }

}


