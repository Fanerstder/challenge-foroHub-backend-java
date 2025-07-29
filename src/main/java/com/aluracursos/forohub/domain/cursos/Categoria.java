package com.aluracursos.forohub.domain.cursos;

/**
 * Enumeración que representa las distintas categorías disponibles para los tópicos del foro.
 * Cada categoría hace referencia a una tecnología o lenguaje de programación reconocido.
 *
 * Usado para clasificar tópicos según el área tecnológica discutida.
 *
 * @author Faner Santander
 * @version 1.0
 */
public enum Categoria {
    HTML,        // Lenguaje de marcado para páginas web
    JAVASCRIPT,  // Lenguaje de programación cliente para la web
    JAVA,        // Lenguaje de programación orientado a objetos
    PHP,         // Lenguaje del lado del servidor para desarrollo web
    PYTHON,      // Lenguaje de propósito general, popular en ciencia de datos y web
    ANGULAR,     // Framework frontend basado en TypeScript
    REACT,       // Librería JavaScript para construir interfaces de usuario
    VUE          // Framework progresivo para interfaces web
}