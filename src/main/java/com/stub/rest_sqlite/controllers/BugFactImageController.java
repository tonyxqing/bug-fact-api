package com.stub.rest_sqlite.controllers;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stub.rest_sqlite.entity.BugFact;
import com.stub.rest_sqlite.repository.BugFactRepository;


@RestController
public class BugFactImageController {
    private static final Logger log = LoggerFactory.getLogger(BugFactImageController.class);
    private final BugFactRepository repository;

    public BugFactImageController(BugFactRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] randomBugFactImage() throws IOException {
        BugFact randomFact = repository.findRandomBugFact();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        File imgFile = new File("Cool-Bug-Facts-Meme.jpg");
        final BufferedImage image = ImageIO.read(imgFile);
        Graphics g = image.getGraphics();
        g.setFont(g.getFont().deriveFont(26f));
        g.setColor(Color.black);

        // Get font metrics to measure text width
        FontMetrics metrics = g.getFontMetrics();
        int maxWidth = image.getWidth() - 320; // Set a margin from the image width
        int x = 300; // Starting x position
        int y = 150; // Starting y position
        String factoid =  randomFact.getFact();
        // Split the text into lines
        String[] paragraphs = factoid.split("\r\n");
        for (String paragraph : paragraphs) {
            log.info("paragraph: " + paragraph);
            String[] words = paragraph.strip().split(" ");
            StringBuilder line = new StringBuilder();
            for (String word : words) {
                log.info(word);
                String testLine = line + word + " ";
                int lineWidth = metrics.stringWidth(testLine);
    
                if (lineWidth > maxWidth) {
                    g.drawString(line.toString(), x, y);
                    line = new StringBuilder(word + " ");
                    y += metrics.getHeight(); // Move to the next line
                } else {
                    line.append(word).append(" ");
                }
            }
            // Draw the last line
            g.drawString(line.toString(), x, y);
            y += metrics.getHeight() * 2;
        }

        g.dispose();
        ImageIO.write(image, "jpg", baos);
        return baos.toByteArray();
    }
}