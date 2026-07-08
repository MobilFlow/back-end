package com.upc.pe.backend.matchmaking.application;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.ResponseCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenAIService {

    private final OpenAIClient client;

    private static final String PROMPT_SISTEMA = """
        Eres un asistente especializado en talleres mecánicos de Lima, Perú.
        El usuario describe un problema con su vehículo en lenguaje natural, puede usar
        jerga peruana o descripciones imprecisas.
        
        Tu tarea es identificar a cuál de estas etiquetas corresponde el problema:
        - frenos: ruidos al frenar, chirridos, pedal esponjoso, vibración al frenar, sonidos extraños al momento de frenar
        - aceite: cambio de aceite, fuga de aceite, aceite negro, humo azul
        - motor: carro no enciende, motor caliente, humo blanco, pérdida de potencia, jalones
        - electronico: check engine, luces del tablero, batería, alternador, falla eléctrica
        - suspension: vibración en el volante, carro baila, ruidos en los huecos, amortiguadores
        - transmision: caja dura, no agarra cambios, resbalos, ruido al cambiar

        Responde ÚNICAMENTE con la etiqueta exacta en minúsculas sin explicación ni puntuación.
        Si no coincide con ninguna etiqueta responde: desconocido
        """;

    public OpenAIService(@Value("${openai.api.key}") String apiKey) {
        this.client = OpenAIOkHttpClient.builder()
                .apiKey(apiKey)
                .build();
    }

    public String detectarEtiqueta(String query) {
        var response = client.responses().create(
                ResponseCreateParams.builder()
                        .model("gpt-4o-mini")
                        .input(PROMPT_SISTEMA + "\n\nProblema del conductor: " + query)
                        .build()
        );

        return response.output().stream()
                .flatMap(item -> item.message().stream())
                .flatMap(msg -> msg.content().stream())
                .flatMap(content -> content.outputText().stream())
                .map(text -> text.text().trim().toLowerCase())
                .findFirst()
                .orElse("desconocido");
    }
}