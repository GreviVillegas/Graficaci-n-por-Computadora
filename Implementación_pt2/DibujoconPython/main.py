import tkinter as tk
from tkinter import colorchooser

class ProgramaDePintura:
    def __init__(self):
        # Configuración de la interfaz gráfica
        self.root = tk.Tk()
        self.marco_control = tk.Frame(self.root)
        self.marco_control.pack(side='top', fill='x')
        
        # Configuración de las opciones de la forma
        self.forma_actual = tk.StringVar(self.root)
        self.forma_actual.set("línea")  # valor inicial
        self.opcion_forma = tk.OptionMenu(self.marco_control, self.forma_actual, "línea", "circunferencia")
        self.opcion_forma.pack(side='left')
        
        # Configuración de las opciones del estilo de línea
        self.estilo_linea = tk.StringVar(self.root)
        self.estilo_linea.set("continuo")  # valor inicial
        self.opcion_estilo = tk.OptionMenu(self.marco_control, self.estilo_linea, "continuo", "segmentado")
        self.opcion_estilo.pack(side='left')
        
        # Configuración del ancho de línea
        self.ancho_linea = tk.Scale(self.marco_control, from_=1, to=10, orient='horizontal', label='Grosor de línea')
        self.ancho_linea.pack(side='left')
        
        # Configuración del botón de selección de color
        self.boton_color = tk.Button(self.marco_control, text="Seleccionar color", command=self.elegir_color)
        self.boton_color.pack(side='left')
        
        # Color actual seleccionado
        self.color_actual = "#000000"  # valor inicial (negro)
        
        # Configuración del lienzo de dibujo
        self.canvas = tk.Canvas(self.root, width=800, height=600)
        self.canvas.pack(fill='both', expand=True)
        
        # Variables para el seguimiento de la forma actual
        self.linea_actual = None
        self.ovalo_actual = None
        
        # Vinculación de eventos del ratón
        self.canvas.bind("<Button-1>", self.iniciar_forma)
        self.canvas.bind("<B1-Motion>", self.dibujar_forma)

    def elegir_color(self):
        # Función para elegir un color
        color = colorchooser.askcolor(title="Seleccionar color")
        if color[1]:  # si el usuario no cancela el cuadro de diálogo
            self.color_actual = color[1]

    def iniciar_forma(self, event):
        # Función para iniciar una forma
        if self.forma_actual.get() == "línea":
            self.linea_actual = self.canvas.create_line(event.x, event.y, event.x, event.y, dash=(3, 5) if self.estilo_linea.get() == "segmentado" else None, width=self.ancho_linea.get(), fill=self.color_actual)
        elif self.forma_actual.get() == "circunferencia":
            self.ovalo_actual = self.canvas.create_oval(event.x, event.y, event.x, event.y, dash=(3, 5) if self.estilo_linea.get() == "segmentado" else None, width=self.ancho_linea.get(), outline=self.color_actual)

    def dibujar_forma(self, event):
        # Función para dibujar una forma
        if self.forma_actual.get() == "línea" and self.linea_actual:
            coords = self.canvas.coords(self.linea_actual)
            self.canvas.coords(self.linea_actual, coords[0], coords[1], event.x, event.y)
        elif self.forma_actual.get() == "circunferencia" and self.ovalo_actual:
            coords = self.canvas.coords(self.ovalo_actual)
            self.canvas.coords(self.ovalo_actual, coords[0], coords[1], event.x, event.y)

    def ejecutar(self):
        # Función para ejecutar el programa
        self.root.mainloop()

if __name__ == "__main__":
    # Instanciar y ejecutar el programa
    ProgramaDePintura().ejecutar()
